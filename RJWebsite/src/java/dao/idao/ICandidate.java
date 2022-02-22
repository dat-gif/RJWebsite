/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao.idao;

import entity.Candidate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USE
 */
public interface ICandidate {

    public ArrayList<Candidate> getTop4Candidate();

    public ArrayList<Candidate> getCandidateListByRecruiterId(int recruiterId);

    public int getNumberPageSearchCandidate(String txtSearch);

    public ArrayList<Candidate> getPaging(int index, String txtSearch);

}
