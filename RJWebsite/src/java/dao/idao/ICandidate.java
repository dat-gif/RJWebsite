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

/**
 *
 * @author Admin
 */
public interface ICandidate {

    public Candidate getCandidateProfileById(int candidateId);

    public CandidateCV getCandidateCVByCandidateId(int candidateId);

    public CandidatePrize getCandidatePrizeByCandidateId(int candidateId);

    public Certificate getCertificateByCandidateId(int candidateId);

    public Education getEducationByCandidateId(int candidateId);

    public Exception getExceptionByCandidateId(int candidateId);

    public Social getSocialByCandidateId(int candidateId);
}
