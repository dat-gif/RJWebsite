/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDao;

import entity.Job;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface IJob {
/**
 * Get first 12 job record in database
 * @return 
 */
    ArrayList<Job> getJobLandingPage();
}
