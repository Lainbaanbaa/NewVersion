package com.qualified_doctor.model;


	
	import java.util.List;



	public class Qualified_doctorService {
	private Qualified_doctorDAO_interface dao;

		public Qualified_doctorService() {
			dao = new Qualified_doctorJDBCDAO();

		}

		public Qualified_doctorVO addQualified_doctor(Integer mem_id,Integer doc_status) {

			Qualified_doctorVO qualified_doctorVO = new Qualified_doctorVO();
			
			qualified_doctorVO.setMem_id(mem_id);
			qualified_doctorVO.setDoc_status(doc_status);

			dao.insert(qualified_doctorVO);

			return qualified_doctorVO;
		}

		
		public Qualified_doctorVO updateQualified_doctor( Integer mem_id,Integer doc_status,Integer doc_id) {

			Qualified_doctorVO qualified_doctorVO = new Qualified_doctorVO();

			qualified_doctorVO.setMem_id(mem_id);
			qualified_doctorVO.setDoc_status(doc_status);
			qualified_doctorVO.setDoc_id(doc_id);
			dao.update(qualified_doctorVO);

			return dao.findByPrimaryKey(doc_id);
		}
		
		//預留給 Struts 2 用的
		public void updateQualified_doctor(Qualified_doctorVO qualified_doctorVO) {
			dao.update(qualified_doctorVO);
		}
	


		public void deleteQualified_doctor(Integer doc_id) {
			dao.delete(doc_id);
		}

		public Qualified_doctorVO getOneQualified_doctor(Integer doc_id) {
			return dao.findByPrimaryKey(doc_id);
		}
		

		public List<Qualified_doctorVO> getAll() {
			return dao.getAll();
		}
		
		public Qualified_doctorVO memidChk(Integer mem_id){
			return dao.memidChk(mem_id);
		}
}

