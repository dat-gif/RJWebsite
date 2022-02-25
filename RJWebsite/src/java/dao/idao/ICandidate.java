/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.idao;

import entity.Candidate;
import entity.CandidateCV;
import entity.CandidatePrize;
import entity.Certificate;
import entity.Education;
import entity.Social;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ICandidate {

    public Candidate getCandidateProfileById(int accountId);

    public CandidateCV getCandidateCVByCandidateId(int candidateId);

    public List<CandidatePrize> getCandidatePrizeByCandidateId(int candidateId);

    public List<Certificate> getCertificateByCandidateId(int candidateId);

    public List<Education> getEducationByCandidateId(int candidateId);

    public List<Exception> getExceptionByCandidateId(int candidateId);

    public List<Social> getSocialByCandidateId(int candidateId);
}
