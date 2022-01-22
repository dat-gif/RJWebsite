/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDao;

import entity.Job;
import entity.Recruiter;
import entity.Skill;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface IJob {

    public ArrayList<Job> getJobLandingPage();

    public ArrayList<Skill> getSkillByJobId(int jobId);

    public ArrayList<String> getSkillNameByJobId(int jobId);

    public Recruiter getRecruterById(int recruterId);

    public Recruiter getRecruiterIdNameById(int recruterId);

}
