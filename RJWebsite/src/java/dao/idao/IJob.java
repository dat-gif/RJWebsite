/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.idao;

import entity.Job;
import entity.Recruiter;
import entity.Skill;
import java.sql.Array;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface IJob {

    public ArrayList<Job> getAllJob(int pageNumber, int recordNumber);

    //Remove in future
    public ArrayList<Job> getJobBySkillAndCity(String skillValue, String cityValue);

    public ArrayList<Job> getJobLandingPage();

    public ArrayList<Skill> getSkillByJobId(int jobId);

    public ArrayList<Skill> getAllSkill();

    public ArrayList<String> getSkillNameByJobId(int jobId);

    public Recruiter getRecruterById(int recruterId);

    public Recruiter getRecruiterIdNameById(int recruterId);

    public void createTempoTableSearchJobData();

    public void insertJobByTextSearch(String txtSearch, String skillValue, String cityValue);

    public void insertJobByFilter(String skillValue, String cityValue);

    public ArrayList<Job> getJobSearching(int pageNumber, int recordNumber);

    public int getTotalJobRow();

    public int getTotalTempJobRow();

    public Job getJobById(int id);

    public ArrayList<Job> getApplyJobPangingByAccountId(int accountId, int currentPage, int recordQuantity);

    public int getTotalApplyJobRow(int candidateId);

    public void sendApplyJob(int jobId, int candidateId);
}
