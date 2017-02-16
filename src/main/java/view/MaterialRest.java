package view;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Material;
import model.MaterialDao;

@RestController
public class MaterialRest {
	/*
	 * The root will show the REST apis available
	 */
    @RequestMapping("/")
    public String api(@RequestParam(value="name", defaultValue="World") String name) {
        return "<a href=\"/test\">/test</a><br><a href=\"/material?id=1\">/material?id=1</a><br><a href=\"/materials\">/materials</a><br><a href=\"index.html\">index.html</a>";
    }
	
    /*
     * Just echo some simple JSON
     */
    @RequestMapping("/test")
    public String hello(@RequestParam(value="name", defaultValue="World") String name) {
        return "{\"id\":\"hello\"}";
    }
    
    /*
     * to avoid cross scripting error we load html-file here
     */
    @RequestMapping("/index.html")
    public String index(@RequestParam(value="name", defaultValue="World") String name) {
		StringBuffer sb = new StringBuffer();
		try{
			Scanner in = new Scanner(new FileReader("index.html"));
			while(in.hasNext()){
				String rivi =in.nextLine();
				sb.append(rivi);
			}
			return sb.toString();	
		}
		catch(Exception e){
			return e.toString();
		}    	
    }
    
    //retrieve material based on id
    @RequestMapping("/material")
    public Material material(@RequestParam(value="id", defaultValue="1") int id) {
        MaterialDao dao = new MaterialDao();
        dao.init();
        dao.initialize(id);
        return dao.getDao();
    }
    
  //retrieve all materials
    @RequestMapping("/materials")
    public List<Material> materials(@RequestParam(value="id", defaultValue="1") int id) {
        MaterialDao dao = new MaterialDao();
        dao.init();
        return dao.getDaos();
    }
    
    /*Method reads json as parameter and stores material to database*/
    @RequestMapping(value="/insertMaterial", method=RequestMethod.POST)
    public Material insertMaterial(HttpServletRequest request, @RequestParam(value="json", defaultValue="") String json) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br;
		try {
			br = request.getReader();
	        String str;
	        while( (str = br.readLine()) != null ){
	            sb.append(str);
	        }    
		} catch (IOException e) {
			e.printStackTrace();
		}
        JsonElement jelement = new JsonParser().parse(sb.toString());  
        //Just testing the content of json
		Writer out=null;
		try{
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("c:/temp/test.txt"), "UTF-8"));
		    out.write(sb.toString());
		    out.flush();
		    out.close();
		}
        //Just testing the content of json
		catch(Exception e){
			System.out.println(e.toString());
		}        
    	MaterialDao dao = new MaterialDao();
    	dao.init();
    	Material material = new Material(); 
        JsonObject myObject = jelement.getAsJsonObject();	
	    material.setCode(myObject.get("code").getAsString());
	    material.setName(myObject.get("name").getAsString());
        material.setPrice(myObject.get("price").getAsInt());	    
	    dao.persist(material);
	    return material;
    }
}
