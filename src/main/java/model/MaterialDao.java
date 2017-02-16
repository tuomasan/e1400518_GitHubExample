package model;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/*
 * Class implements DAO (data access object) method to CRUD POJO to database
 */
public class MaterialDao {
	private Material dao;
	
	public Material getDao() {
		return dao;
	}
	
	public void setDao(Material dao) {
		this.dao = dao;
	}
	
	private EntityManager entityManager;
	
	public void init(){
		entityManager = Persistence.createEntityManagerFactory("w9").createEntityManager();
	}
	
	public List<Material> getDaos(){
		entityManager.getTransaction().begin();
		Query query = entityManager.createNamedQuery("Material.findAll");
		//Query query = entityManager.createQuery("from Product c", Product.class);
		List<Material> result = query.getResultList(); 
		entityManager.getTransaction().commit();
		return result;
	}
	
	public int persist(Material dao){
		entityManager.getTransaction().begin();
		entityManager.persist(dao);
		entityManager.getTransaction().commit();
		return dao.getId();
	}
	
	public void initialize(int daoNumber){
		dao = entityManager.find(Material.class, daoNumber);
		  if(dao == null)throw new IllegalStateException
		   ("Dao number ("+daoNumber+") not found");		
	}
	
	public void update(Material _dao){
		//just checking that the dao really has is
		if(dao.getId()>0){
			//get the actual entity from database to a dao-named attribute
			initialize(dao.getId());
			//start database transaction
			entityManager.getTransaction().begin();
			dao.setCode(_dao.getCode());
			dao.setName(_dao.getName());
			dao.setPrice(_dao.getPrice());
			entityManager.merge(dao);
			entityManager.getTransaction().commit();
		}
	}
	
	public void delete(){
		entityManager.getTransaction().begin();
		entityManager.remove(dao);
		entityManager.getTransaction().commit();
	}
	
	public void destroy(){
		entityManager.close();
	}
}
