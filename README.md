# test_spring_mvc
A repository which includes an simple example of spring mvc and thymeleaf. This is a progresive project, each branch shows an improvement in the prohect or the addition of a new feature 

## Master branch 
Master branch includes a simple project with no data base connection 
Only a simple bean and a view to show how to interact with spring mvc for the first time

## Add-bootstrap branch
Add-bootstrap branch shows an example how to integrate boostrap 4 and create some components on view

## Style-sass branch
Shows an example of styling in views using sass files, notices that you have to install a external compiler of sass in order to modify the sass file and compile it into a css stylesheet 

## add-jpa-h2-create-query branch
In this branch is created a simple entity, DAO interface and its implementation, and the conection using JPA and H2 database. It is created a sql file taht inserts some registers into H2 database and they are shown in a view.

## edit-session-atributtes branch
The controller is modifiead to create a session property that helps to get an object from the view and keeps its state. Session attributes are similar to use a hidden input in the view, but more private and controlled 

## form-validation branch
Annotations of validations are added the entity, messages.properties has the custon messages for the validations and they are shown in the view. Form view iterates errors collections and each field are capable to show the corresponding validation message in case of error.

## delete-action branch
Delete action is created 

## service branch
A service interface is created to manage the CRUD operations instead of use the dao interface directly. This form of manage the operation is called Facade pattern.

## crud-repository-interface branch
Dao interface extends from CrudRepository interface and the implementation of dao is deleted. CrudRepository allows to use all basic CRUD repository avoiding the implementations of each method.

## MySQL-Connection
The MySQl connection is created. Ypu need to install MySQL server from the official page, install the java connector to run this branch ad create the scheme. All configuration is placed in application.properties.

