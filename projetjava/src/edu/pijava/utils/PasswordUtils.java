/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    // méthode pour hacher le mot de passe
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // méthode pour vérifier si le mot de passe en clair correspond au mot de passe haché
    public static boolean checkPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}