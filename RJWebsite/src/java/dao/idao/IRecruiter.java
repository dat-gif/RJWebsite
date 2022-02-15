/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.idao;

import entity.Job;
import entity.Recruiter;
import entity.Skill;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface IRecruiter {

    public Recruiter getRecruiterById(int recruterId);

    public void createRecruiterTempoTableSearchData();

    public void insertRecruiter(String txtSearch, String cityValue);

    public void insertRecruiterFilterByCity(String cityValue);

    public ArrayList<Recruiter> getRecruiterPaging(int pageNumber, int recordNumber);

    public ArrayList<Recruiter> getAllRecruiter(int pageNumber, int recordNumber);

    public int getTotalRecruiterRow();

    public int getTotalTempRecruiterRow();

    public ArrayList<String> getSkillNameByRecruiterId(int jobId);

    public ArrayList<Skill> getRecruiterSkill(int recruiterId);

    public int getCandidateTotalFollowingCompany(int candidateId);

    public boolean checkRecruiterBeenFollowing(int recruiterId, int candidateId);

    public void createRequestFollowingCompany(int recruiterId, int candidateId);

    public void deleteRequestFollowingCompany(int recruiterId, int candidateId);

    public ArrayList<Recruiter> getCandidateFollowingRecruiterList(int accountId, int currentPage, int recordQuantity);
}
