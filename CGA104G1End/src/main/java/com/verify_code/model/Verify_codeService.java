package com.verify_code.model;


	
	import java.util.List;



	public class Verify_codeService {
	private Verify_codeDAO_interface dao;

		public Verify_codeService() {
			dao = new Verify_codeJDBCDAO();

		}

		public Verify_codeVO addVc_code(Integer mem_id ,String vc_code) {

			Verify_codeVO verify_codeVO = new Verify_codeVO();
			

			verify_codeVO.setMem_id(mem_id);
			verify_codeVO.setVc_code(vc_code);

			dao.insert(verify_codeVO);

			return verify_codeVO;
		}

		//預留給 Struts 2 或 Spring MVC 用
		public void addVc_code(Verify_codeVO verify_codeVO) {
			dao.insert(verify_codeVO);
		}
		
		public Verify_codeVO updateVerify_code(Integer mem_id ,String vc_code,Integer vc_id) {

			Verify_codeVO verify_codeVO = new Verify_codeVO();

			verify_codeVO.setMem_id(mem_id);
			verify_codeVO.setVc_code(vc_code);
			verify_codeVO.setVc_id(vc_id);
			
			dao.update(verify_codeVO);

			return dao.findByPrimaryKey(vc_id);
		}
		
		//預留給 Struts 2 用的
		public void updateVerify_codeVO(Verify_codeVO verify_codeVO) {
			dao.update(verify_codeVO);
		}
	


		public void deleteVerify_codeVO(Integer mem_id) {
			dao.delete(mem_id);
		}

		public Verify_codeVO findByPrimaryKey(Integer vc_id) {
			return dao.findByPrimaryKey(vc_id);
		}
		

		public List<Verify_codeVO> getAll() {
			return dao.getAll();
		}
		
	    public Verify_codeVO checkVerify_code(Integer mem_id) {
	    	return dao.checkVerify_code(mem_id);
	    }

}

