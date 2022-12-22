package com.verify_code.model;

import java.util.List;

public interface Verify_codeDAO_interface {
	public void insert(Verify_codeVO verify_codeVO);
    public void update(Verify_codeVO verify_codeVO);
    public void delete(Integer mem_id);
    public Verify_codeVO findByPrimaryKey(Integer vc_id);
    public List<Verify_codeVO> getAll();
    public Verify_codeVO checkVerify_code(Integer mem_id);
}
