/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.idao;

import entity.Account;
import entity.Recruiter;

/**
 *
 * @author Admin
 */
public interface IAccount {

    public Account getAccountByEmailAndPassword(String email, String password);

    public boolean checkExistAccountEmail(String email);

    public void insertCandidateAccount(Account account);

    public void insertRecruitorAccount(Account account, Recruiter recruoterBasicInfo);

}
