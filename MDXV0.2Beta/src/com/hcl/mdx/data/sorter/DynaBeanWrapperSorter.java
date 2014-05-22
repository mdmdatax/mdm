package com.hcl.mdx.data.sorter;

import org.apache.commons.beanutils.DynaBean;

import com.hcl.mdx.data.model.MDXDynaBeanWrapper;

public class DynaBeanWrapperSorter extends AbstractSorter{
	
	@Override
	public int compare(Object o1, Object o2) {
				
		MDXDynaBeanWrapper beanWrapper1 = (MDXDynaBeanWrapper) o1;
		DynaBean bean1 = beanWrapper1.getDynaBean();
		MDXDynaBeanWrapper beanWrapper2 = (MDXDynaBeanWrapper) o2;
		DynaBean bean2 = beanWrapper2.getDynaBean();
		
		int sortValue = 0;
		
		if((bean1.get(sortProperty) == null) && (bean2.get(sortProperty) == null)){
			sortValue = 0;
			System.out.println(sortProperty + ":I " + bean1.get(sortProperty)+", " + bean2.get(sortProperty)+": "+sortValue);
		}
		else if(bean1.get(sortProperty) == null){
			sortValue = -1;
			System.out.println(sortProperty + ":1 " + bean1.get(sortProperty)+", " + bean2.get(sortProperty)+": "+sortValue);
		}
		else if(bean2.get(sortProperty) == null){
			sortValue = 1;
			System.out.println(sortProperty + ":2 " + bean1.get(sortProperty)+", " + bean2.get(sortProperty)+": "+sortValue);
		}
		else{
			Object value1 = bean1.get(sortProperty);
			Object value2 = bean2.get(sortProperty);
			boolean sortCompleted = false;
			
			try{
				double numericValue1 = Double.parseDouble(value1.toString());
				double numericValue2 = Double.parseDouble(value2.toString());
				if(numericValue1 > numericValue2) sortValue = 1;
				else if(numericValue2 > numericValue1) sortValue = -1;
				else sortValue = 0;
				
				sortCompleted = true; 
			}
			catch(Exception e){
				
			}
			
			if(!sortCompleted) {
				sortValue = value1.toString().compareTo(value2.toString());
			
			}
			
		}
		
		return ascending?sortValue:-(sortValue);
	}

}
