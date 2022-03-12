/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.idao;

import entity.Candidate;
import entity.Job;
import entity.Recruiter;
import entity.Skill;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IJob {

    public List<Candidate> getCandidateApplyJob(int id, int index, int size);

    public List<Candidate> getCandidateApplyJobSearch(int id, String txtSearch, int index, int size);

    public int countTotalApplyRow(int id);

    public int countTotalApplySearchRow(int id, String txtSearch);

    public void updateStatus(int id, boolean status);

    public ArrayList<Job> getAllJob(int pageNumber, int recordNumber);

    public List<Job> getJobs(int index, int size);

    public List<Job> getJobDashboardSearching(String txtSearch, int index, int size);

    public int countTotalJobSearch(String txtSearch);

    public int countTotalJob();
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

    public boolean checkJobBeenApply(int jobId, int cadidateId);

    public void editRequestStatusApplyJob(int jobId, int candidateId, String status);

    public void createRequestApplyJob(int jobId, int candidateId);

    public void deleteRequestApplyJob(int jobId, int candidateId);

    public int insertRecruitment(int recruiterId, String title, String description, String salary, String quantity, String role, String experience, String location, String hiredDate);

    public int getLatestInsertedJobId();

    public int insertJobSkill(int jobId, int skillId);

    public int updateJob(int jobId, String title, String description, String salary, String quantity, String role, String experience, String location, String hiredDate);

    public ArrayList<Job> getJobByRecruiterId(int recruiterId);

    public int deleteJob(int jobId);
}
