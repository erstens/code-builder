## WHAT

  Semi-automated code generation tool .
  Given a template(for beetl),set the db conf,you will get some code .

## HOW TO

 -   ### First 
 
      - db.url
      - db.user
      - db.pwd
      - db.tableName
      
 -   ### Second
      - import template , if you have common code file ,you can use the via to replace string .
      
 -   ### Third
      - see the test class , run it .
 
  
 
## DOC

 -   ### 1.YOU KNOWN
      - file input path
      
            /resources/template
      - file output path
              
            /target/classes/_out
            
 -   ### 2.VIA
 
       - dbTableName
        
            it is table name .
       
             sys_user,sys_module,sys_role...,
       - columns
               
            it is table column data warp in map ,detail see 'view.btl' .
