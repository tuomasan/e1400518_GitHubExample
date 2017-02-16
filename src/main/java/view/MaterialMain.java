package view;

import model.Material;
import model.MaterialDao;

public class MaterialMain {
	public static void main(String[] args) {
		MaterialDao b = new MaterialDao();		//create connection with database
		b.init();								//initiate database connection
		b.initialize(1);						//find material id 1
		Material c = b.getDao();				//use material here
		c.setCode("ABCD");						//update material data
		c.setName("Laite ABCD");
		c.setPrice(1222);
		b.update(c);							//save changes
		b.initialize(c.getId());				//fetch the same material from database
		System.out.println(b.getDao().getName()); 
		b.destroy();							//close database connection
	}

}
