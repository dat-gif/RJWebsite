/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDao;

import entity.Job;
import entity.Recruiter;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface IRecruiter {

    public Recruiter getRecruterById(int recruterId);

    public void createRecruiterTempoTableSearchData();

    public void insertRecruiter(String txtSearch, String cityValue);

    public ArrayList<Recruiter> getRecruterPaging(int pageNumber, int recordNumber);

    public ArrayList<Recruiter> getAllRecruter(int pageNumber, int recordNumber);

    public int getTotalJobRow();

    public int getTotalTempJobRow();

}
