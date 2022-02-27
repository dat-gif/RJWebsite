/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

 */
package dao.idao;

import entity.Candidate;


import java.util.ArrayList;
import entity.CandidateCV;
import entity.CandidatePrize;
import entity.Certificate;
import entity.Education;
import entity.Experience;
import entity.Skill;
import entity.Social;

import java.util.List;

/**
 *
 * @author Admin
 */
public interface ICandidate {

    public List<Candidate> getCandidates();

    public Candidate getCandidateProfileById(int accountId);

    public CandidateCV getCandidateCVByCandidateId(int candidateId);

    public List<CandidatePrize> getCandidatePrizeByCandidateId(int candidateId);

    public List<Certificate> getCertificateByCandidateId(int candidateId);

    public List<Education> getEducationByCandidateId(int candidateId);

    public List<Experience> getExperienceByCandidateId(int candidateId);

    public List<Social> getSocialByCandidateId(int candidateId);

    public List<Skill> getSkillByCandidateId(int candidateId);
    
    public ArrayList<Candidate> getTop4Candidate();

    public ArrayList<Candidate> getCandidateListByRecruiterId(int recruiterId);

    public int getNumberPageSearchCandidate(String txtSearch, String city);

    public ArrayList<Candidate> getCandidateSearchPaging(int index, String txtSearch, String city);

}
