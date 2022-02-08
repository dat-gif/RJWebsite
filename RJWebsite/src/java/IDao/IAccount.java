/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDao;

import entity.Account;

/**
 *
 * @author Admin
 */
public interface IAccount {

    public Account getAccountByEmailAndPassword(String email, String password);

}
