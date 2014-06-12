/*
 * Copyright (C) 2014 Amit & Ravi
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package features;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @author amit
 */
public class CompilerLinking {
     public void compileFile(File file) throws IOException
    {
       
    try{    
            //For checking if the code is already appended in the file
            boolean appendedCodeAlreadyPresent = false;
            FileReader fr = new FileReader(file.getAbsolutePath());
            BufferedReader forcheck = new BufferedReader(fr);
            String s=forcheck.readLine();
            while(s!=null)
            {
                if(s.equals(";Warning:Do not alter this code"))
                    appendedCodeAlreadyPresent = true;
                s = forcheck.readLine();
            }
    
            try {
                if(appendedCodeAlreadyPresent == false)
                {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath(), true)));
                                        out.println(";Warning:Do not alter this code"+"\n"
                                       +"mov eax,29"+"\n"
                                       +"int 80h"+"");
                    out.close();
                }
} catch (IOException e) {
    //exception handling left as an exercise for the reader
}
          
            String fullName=file.getName();
            System.out.println(fullName);
            String name;
            int index=file.getName().lastIndexOf('.');
            
            if(index>0 && index<=file.getName().length()-2)
                name=file.getName().substring(0, index);
            else
                name=file.getName();
            
            String dir1=file.getParent();
            
            String[] command = {"xterm","-e","nasm -f elf64 " + fullName};
            
           ProcessBuilder pb= new ProcessBuilder(command);
           pb.directory(new File(dir1));
           Process p=pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String Error;
            while ((Error = stdError.readLine()) != null) {
                System.out.println(Error);
            }
            while ((Error = stdInput.readLine()) != null) {
                System.out.println(Error);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
            
   
    }
    
    public void run(File file)
    {
        try {
            String fullName=file.getName();
            String name;
            int index=file.getName().lastIndexOf('.');
            
            if(index>0 && index<=file.getName().length()-2)
                name=file.getName().substring(0, index);
            else
                name=file.getName();
   
            String dir1=file.getParent();
          String obj= name+".o"; 
          String[] command = {"xterm","-e","ld -o out " + obj};
          System.out.print(command);
           ProcessBuilder pb= new ProcessBuilder(command);
           pb.directory(new File(dir1));
           Process p=pb.start();
          
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String Error;
            while ((Error = stdError.readLine()) != null) {
                System.out.println(Error);
            }
            while ((Error = stdInput.readLine()) != null) {
                System.out.println(Error);
            }
            if(Error==null)
                execution(dir1);
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void execution(String dir1)
    {
        try{
                   
           String[] command = {"xterm","-e","./out"};
           ProcessBuilder pb= new ProcessBuilder(command);
           pb.directory(new File(dir1));
           Process p=pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String Error;
            while ((Error = stdError.readLine()) != null) {
                System.out.println(Error);
            }
            while ((Error = stdInput.readLine()) != null) {
                System.out.println(Error);
            }
          
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
   
    
}
