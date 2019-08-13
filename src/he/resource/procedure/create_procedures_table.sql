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
/*create table HE_INPUTS(
id number primary key,
M number(1) not null,
L number(1) not null,
T number(1) not null,
K number(1) not null,
A number(1) not null,
name varchar2(32) not null,
symbol varchar(10) not null,
description varchar(256)
);
*/
/*----------------------- mySQL ----------------------------------*/
create table HE_PROCETURES(
id int AUTO_INCREMENT primary key,
inputids text not null,
formulaids text not null,
name varchar(32) not null unique,
description text
);