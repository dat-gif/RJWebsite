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

    public void updateSkill(Skill s);

    public Skill getSkillById(int Id);

    public void insertSkill(Skill s);

    public void removeSkill(int Id);

    public void DeleteRecruiterSkill(int Id);

    public void DeleteCandidateSkill(int Id);

    public void DeleteJobSkill(int Id);
}
