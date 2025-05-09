create table tax_b1 (
	guid varchar(50) NOT NULL,
	state varchar(2) NOT NULL,
    tributacao varchar(40) NOT NULL,
    ncm_code varchar(20) NOT NULL,
    efct_from datetime NOT NULL,
    efct_to datetime,
    utilizacao bigint NOT NULL,
    tax_code varchar(10) NOT NULL,
    cfop_out varchar(5) NOT NULL,
    icms decimal(13,6) NOT NULL,
    pis decimal(13,6) NOT NULL,
    cofins decimal(13,6) NOT NULL,
    cst_icms varchar(2) NOT NULL,
    cst_pis varchar(2) NOT NULL,
    cst_cofins varchar(2) NOT NULL,
    process_date datetime NOT NULL,
    last_send_date datetime
    /*UNIQUE KEY taxb1_uk (state,ncm)*/
) engine=InnoDB default charset=utf8;

create table item_b1 (
	id bigint not null auto_increment,
	guid varchar(50) NOT NULL,
	doc_entry bigint NOT NULL,
	trans_type varchar(1) NOT NULL,
    obj_type bigint NOT NULL,
    item_code varchar(50) NOT NULL,
    item_name varchar(200) NOT NULL,
    item_class bigint NOT NULL,
    valid_for varchar(1) NOT NULL,
    valid_from datetime,
    valid_to datetime,
    frozen_for varchar(1) NOT NULL,
    frozen_to datetime,
	frozen_from datetime,
	sell_item varchar(1) NOT NULL,
	create_date datetime NOT NULL,
	code_bars varchar(100),
	ncm_code varchar(20) NOT NULL,
	suom_entry varchar(100),
	u_cmzb1_categ varchar(10),
	sal_unit_msr varchar(10),
	num_in_sale decimal(13,6),
	sal_pack_msr varchar(10),
	sal_pack_unit decimal(13,6),
	u_cmzb1_venda_unit varchar(1),
	product_src varchar(10),
	cest varchar(10), 
	update_date datetime,
	last_send_date datetime,
	last_send_date_imp datetime,
    primary key(id),
    UNIQUE KEY itemb1_uk (item_code,ncm_code)
) engine=InnoDB default charset=utf8;

create table item_price_b1 (
	id bigint not null auto_increment,
	guid varchar(50) NOT NULL,
    item_code varchar(50) NOT NULL,
    price_list varchar(10) NOT NULL,
    price decimal(13,6) NOT NULL,
    cost_price decimal(13,6) NOT NULL,
	uom_code varchar(20) NOT NULL,
	update_date datetime,
	last_send_date datetime,
    primary key(id),
    UNIQUE KEY itemb1_uk (item_code,price_list)
) engine=InnoDB default charset=utf8;

create table item_price_list_b1 (
	id bigint not null auto_increment,
	guid varchar(50) NOT NULL,
    price_list varchar(10) NOT NULL,
	list_name varchar(100) NOT NULL,
	valid_for varchar(1) NOT NULL,
    valid_from datetime,
    valid_to datetime,
	update_date datetime,
	last_send_date datetime,
    primary key(id),
    UNIQUE KEY itemb1_uk (price_list)
) engine=InnoDB default charset=utf8;

create table partner_b1 (
	guid varchar(50) NOT NULL,
	doc_entry bigint NOT NULL,
	trans_type varchar(1) NOT NULL,
    obj_type bigint NOT NULL,
    card_code varchar(20) NOT NULL,
    card_name varchar(150) NOT NULL,
    card_f_name varchar(100) NOT NULL,
    card_type varchar(1) NOT NULL,
    valid_for varchar(1) NOT NULL,
    valid_to datetime ,
    valid_from datetime,
    frozen_for varchar(1) NOT NULL,
    frozen_to datetime,
	frozen_from datetime,
	alias_name varchar(100),
	free_text varchar(150),
	date_till datetime,
	lang_code bigint NOT NULL,
	create_date datetime NOT NULL,
	update_date datetime,
    update_date_master datetime,
	street varchar(100),
	block varchar(150),
	city varchar(100),
	state varchar(2),
	zip_code varchar(20),
	tax_id0 varchar(50),
	tax_id4 varchar(50),
	credit_line varchar(50) NOT NULL,
	balance decimal(13,6) NOT NULL,
	last_send_date datetime,
    UNIQUE KEY itemb1_uk (doc_entry)
) engine=InnoDB default charset=utf8;

create table category_b1 (
	id bigint not null auto_increment,
	code varchar(10) NOT NULL,
    name varchar(150) NOT NULL,
    create_date datetime,
    update_date datetime,
    update_date_master datetime,
	last_send_date datetime,
    primary key(id),
    UNIQUE KEY categoryb1_uk (code)
) engine=InnoDB default charset=utf8;

/*Tabela de controle de instancias de execução de Schedule*/
CREATE TABLE shedlock (
  name VARCHAR(64),
  lock_until TIMESTAMP(3) NULL,
  locked_at TIMESTAMP(3) NULL,
  locked_by VARCHAR(255),
  PRIMARY KEY (name)
);