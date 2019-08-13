/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Digvijay Gavas
 * Created: Oct 1, 2016
 */
/*----------------------- Oracle ---------------------------------*/
/*create table HE_FORMULAE(
id number primary key,
input_ids varchar(512) not null,
relation varchar(512) not null,
classpath varchar(256) not null,
name varchar2(32) not null,
description varchar(256)
);*/

/*----------------------- mySQL ----------------------------------*/
create table HE_FORMULAE(
id int AUTO_INCREMENT primary key,
inputids text not null,
relation text not null,
classpath varchar(256) not null,
name varchar(32) not null unique,
description text
);
