package com.hcl.mdx.zk.ui.composer;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.model.Table;
import org.apache.log4j.Logger;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Progressmeter;
import org.zkoss.zul.Toolbarbutton;

import com.hcl.mdx.data.model.SessionDetailsObject;
import com.hcl.mdx.database.util.ListOfTables;
import com.hcl.mdx.file.FileHandler;
import com.hcl.mdx.task.executor.SchemaBuildTaskExecutor;
import com.hcl.mdx.task.executor.SchemaFileParseTaskExecutor;
import com.hcl.mdx.task.objects.ProgressMessageObject;
import com.hcl.mdx.task.objects.SchemaFileParseTaskInput;
import com.hcl.mdx.util.ActionRequired;
import com.hcl.mdx.util.Constants;
import com.hcl.mdx.zk.data.manager.SchemaTableDataManager;
import com.hcl.mdx.zk.ui.event.listener.OnClickEventListenerForDBCreation;
import com.hcl.mdx.zk.ui.status.renderers.ProgressRenderer;

public class CreateSchemaPageComposer extends AbstractPageComposer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("CreateSchemaPageComposer");
	private Panel topPanel;
	private Panel middlePanel;
	private Toolbarbutton homeButton;
	private Progressmeter dataTransferMeter;
	private Label processStatus;
	private Label processActivity;
	private Label timeElapsed;
	private Panelchildren dataViewPanelChildren;
	private Button dbCreateButton;
	private static int PAGE_ID = 2;
	private static String PAGE_URL = "createSchema.zul";

	@Override
	public int getPageId() {
		// TODO Auto-generated method stub
		return PAGE_ID;
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}

	public void onCreate$window(Event event) throws Exception {
		boolean paramsSet = checkSessionParameters(event,
				CreateSchemaPageComposer.PAGE_URL);

		if (paramsSet) {
			HttpSession httpSession = (javax.servlet.http.HttpSession) Sessions
					.getCurrent().getNativeSession();
			SessionDetailsObject sessionDetailsObject = (SessionDetailsObject) httpSession
					.getAttribute(Constants.SESSION_DETAILS_OBJECT_NAME);

			Grid schemaTableGrid = sessionDetailsObject
					.getSchemaTableGridBuilder().buildGrid();
			if (schemaTableGrid != null)
				dataViewPanelChildren.appendChild(schemaTableGrid);

			dbCreateButton = (Button) ((Component) (((Component) (dataViewPanelChildren
					.getFirstChild().getChildren().get(1))).getChildren()
					.get(1))).getChildren().get(2);

			dbCreateButton.addEventListener(Events.ON_CLICK,
					new OnClickEventListenerForDBCreation(this));
			self.setAttribute(Constants.PAGE_COMPOSER_PROPERTY_NAME, this);
		}

	}

	public void onClick$fileUploadButton(Event event)
			throws InterruptedException {

		HttpSession httpSession = (javax.servlet.http.HttpSession) Sessions
				.getCurrent().getNativeSession();

		java.io.InputStream fileDataStream = null;

		try {
			Media uploadedFile = Fileupload.get(true);

			fileDataStream = uploadedFile.getStreamData();

			String sessionId = httpSession.getId();

			FileHandler fileHandler = new FileHandler();
			Constants.TMP_FOLDER_PATH = httpSession.getServletContext()
					.getRealPath("/");
			String uploadStatus = fileHandler.handleFile(
					uploadedFile.getName(), sessionId, fileDataStream);

			if (uploadStatus.compareTo("1") == 0) {
				String infoMessage = "File Type: "
						+ uploadedFile.getContentType() + "\n"
						+ "File Format: " + uploadedFile.getFormat() + "\n"
						+ "File Name: " + uploadedFile.getName();

				SessionDetailsObject sessionDetailsObject = (SessionDetailsObject) httpSession
						.getAttribute(Constants.SESSION_DETAILS_OBJECT_NAME);
				sessionDetailsObject.setXmlSchemaFilePath(fileHandler
						.getFilePath());

				httpSession.setAttribute(Constants.SESSION_DETAILS_OBJECT_NAME,
						sessionDetailsObject);

				final ActionRequired actionRequired = new ActionRequired();
				Messagebox.show(infoMessage, "Success!", Messagebox.OK,
						Messagebox.INFORMATION,
						new org.zkoss.zk.ui.event.EventListener() {
							public void onEvent(Event event) throws Exception {
								if (Events.ON_OK.equals(event.getName())) {
									event.getTarget().detach();
									actionRequired.setActionRequired(true);
								}
							}
						});

				if (actionRequired.isActionRequired()) {
					desktop.enableServerPush(true);
					topPanel.setOpen(false);
					middlePanel.setOpen(false);
					progressPanel.setVisible(true);
					homeButton = (Toolbarbutton) Path
							.getComponent("//navToolBar/homeButton");
					homeButton.focus();

					ProgressMessageObject messageObject = new ProgressMessageObject();
					ProgressRenderer progressRenderer = new ProgressRenderer(
							desktop, dataTransferMeter, messageObject,
							processStatus, processActivity, timeElapsed, null);

					progressRenderer.start();

					SchemaFileParseTaskExecutor schemaFileParseTaskExecutor = new SchemaFileParseTaskExecutor(
							sessionDetailsObject, httpSession);
					schemaFileParseTaskExecutor
							.setProgressMessageObject(messageObject);
					SchemaFileParseTaskInput fileParseTaskInput = new SchemaFileParseTaskInput();
					fileParseTaskInput.setFilePath(fileHandler.getFilePath());
					schemaFileParseTaskExecutor
							.setTaskInput(fileParseTaskInput);

					new Thread(schemaFileParseTaskExecutor).start();

				}
			} else {

				Messagebox.show(uploadStatus, "Failure!", Messagebox.OK,
						Messagebox.ERROR, null);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onClick$executeDatabaseScriptsButton(Event event) {
		HttpSession httpSession = (javax.servlet.http.HttpSession) Sessions
				.getCurrent().getNativeSession();
		SessionDetailsObject sessionDetailsObject = (SessionDetailsObject) httpSession
				.getAttribute(Constants.SESSION_DETAILS_OBJECT_NAME);
		try {
			desktop.enableServerPush(true);
			topPanel.setOpen(false);
			middlePanel.setOpen(false);
			progressPanel.setVisible(true);
			homeButton = (Toolbarbutton) Path
					.getComponent("//navToolBar/homeButton");
			homeButton.focus();

			ProgressMessageObject messageObject = new ProgressMessageObject();
			ProgressRenderer progressRenderer = new ProgressRenderer(desktop,
					dataTransferMeter, messageObject, processStatus,
					processActivity, timeElapsed, null);

			ArrayList<String> listOfTables = new ListOfTables()
					.getListOfTables(Constants.CONNECTION_DETAILS_OBJECT);
			ArrayList<String> listOfDuplicateTables = new ArrayList<String>();

			progressRenderer.start();

			Database database = ((SchemaTableDataManager) (sessionDetailsObject
					.getSchemaTableGridBuilder().getDataManager()))
					.getDatabase();

			Table[] tables = database.getTables();

			for (Table table : tables) {
				for (String listOfTable : listOfTables) {
					if (listOfTable.equalsIgnoreCase(table.getName())) {
						listOfDuplicateTables.add(table.getName());
					}
				}
			}

			if (listOfDuplicateTables.size() > 0) {
				StringBuilder dupBuilder = new StringBuilder();
				for (int i = 0; i < listOfDuplicateTables.size(); i++) {
					if (i < 1) {
						dupBuilder.append(listOfDuplicateTables.get(i));
					} else {
						dupBuilder.append("," + listOfDuplicateTables.get(i));
					}
				}
				String infoMessage = "Tables" + dupBuilder.toString()
						+ "Already Exist Do you want to Recreate it";
				final ActionRequired actionRequired = new ActionRequired();
				Messagebox.show(infoMessage, "Warning", Messagebox.YES
						| Messagebox.NO, Messagebox.INFORMATION,
						new org.zkoss.zk.ui.event.EventListener() {
							public void onEvent(Event event) throws Exception {
								System.out.println(event.getName());
								if ("onYes".equalsIgnoreCase(event.getName())) {
									System.out.println(event.getName());
									event.getTarget().detach();
									actionRequired.setActionRequired(true);
								}
							}
						});
				if (actionRequired.isActionRequired()) {
					StringBuilder builder = new StringBuilder();
					for (int i = 0; i < listOfDuplicateTables.size(); i++) {
						if (i < 1) {
							builder.append(listOfDuplicateTables.get(i));
						} else {
							builder.append("|" + listOfDuplicateTables.get(i));
						}
					}
					String logicalTables = builder.toString();
					boolean status = new ListOfTables().callDropFunction(
							logicalTables, Constants.CONNECTION_DETAILS_OBJECT);
					if (status) {

						SchemaBuildTaskExecutor buildTaskExecutor = new SchemaBuildTaskExecutor(
								sessionDetailsObject, httpSession);
						buildTaskExecutor
								.setProgressMessageObject(messageObject);
						new Thread(buildTaskExecutor).start();
					} else {
						Messagebox.show("Probelem In Deleting The Tables", "Failure!", Messagebox.OK,
								Messagebox.ERROR, null);
					}
				} else {
					infoMessage = "Error:Please Check Your Xml File";
					Messagebox.show(infoMessage, "Failure!", Messagebox.OK,
							Messagebox.ERROR, null);

				}
			} else {

				SchemaBuildTaskExecutor buildTaskExecutor = new SchemaBuildTaskExecutor(
						sessionDetailsObject, httpSession);
				buildTaskExecutor.setProgressMessageObject(messageObject);

				new Thread(buildTaskExecutor).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
