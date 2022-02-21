/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import dao.idao.ICandidate;
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
public class CandidateDAO extends DBContext implements ICandidate{

    @Override
    public Candidate getCandidateProfileById(int candidateId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CandidateCV getCandidateCVByCandidateId(int candidateId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CandidatePrize getCandidatePrizeByCandidateId(int candidateId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Certificate getCertificateByCandidateId(int candidateId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Education getEducationByCandidateId(int candidateId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Exception getExceptionByCandidateId(int candidateId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Social getSocialByCandidateId(int candidateId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
