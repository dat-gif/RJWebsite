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
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ISkill {

    public void updateStatus(int id, boolean status);

    public int countTotalSkillSearch(String txtSearch);

    public int countTotalSkill();

    public List<Skill> getSkillDashboardSearching(String txtSearch, int index, int size);

    public List<Skill> getSkills(int index, int size);

    public boolean checkExistedSkillName(String n);

    public void updateSkill(Skill s);

    public Skill getSkillById(int Id);

    public void insertSkill(Skill s);
}
