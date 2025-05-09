package com.seidor.comerzzia.connector.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.seidor.comerzzia.connector.domain.model.ItemB1;

import jakarta.persistence.Tuple;

@Repository
public interface ItemB1Repository extends JpaRepository<ItemB1, ItemB1.pk_itemB1> {
	
	public Optional<ItemB1> findByItemCodeAndNcmCode(String itemCode, String ncmCode);
	
	public List<ItemB1> findByItemCodeInAndNcmCodeIn(List<String> itemsCode, List<String> ncmsCode);
	
	@Query(value = 
			"select tax.state "
			+ ", item.item_code "
			+ ", item.ncm_code "
			+ ", tax.icms "
			+ ", tax.pis "
			+ ", tax.cofins "
			+ ", tax.cst_icms "
			+ ", tax.cst_pis"
			+ ", tax.cst_cofins "
			+ ", item.u_cmzb1_categ "
			+ ", item.cest "
			+ ", item.product_src "
			+ "from item_b1 item "
			+ "inner join tax_b1 tax "
			+ "on item.ncm_code = tax.ncm_code "
			+ "where item.update_date > item.last_send_date_imp or item.last_send_date_imp is null "
			+ "and item.u_cmzb1_categ is not null", nativeQuery = true)
	public List<Tuple> findItemTaxes();
	
	@Query(value = 
			"select tax.state "
			+ ", item.item_code "
			+ ", item.ncm_code "
			+ ", tax.icms "
			+ ", tax.pis "
			+ ", tax.cofins "
			+ ", tax.cst_icms "
			+ ", tax.cst_pis"
			+ ", tax.cst_cofins "
			+ ", item.u_cmzb1_categ "
			+ ", item.cest "
			+ ", item.product_src "
			+ "from item_b1 item "
			+ "inner join tax_b1 tax "
			+ "on item.ncm_code = tax.ncm_code "
			+ "where tax.last_send_date < tax.process_date "
			+ "or tax.last_send_date is null "
			+ "and item.u_cmzb1_categ is not null", nativeQuery = true)
	public List<Tuple> findItemTaxesDynamics();
	
	@Query(value = "select it.item_code, it.item_name, it.code_bars, cat.code as category, cat.name,"
			+ "IF(it.u_cmzb1_venda_unit = 'Y', it.sal_pack_msr, it.sal_unit_msr) as unit "
			+ "from item_b1 it "
			+ "inner join category_b1 cat "
			+ "on it.u_cmzb1_categ = cat.code "
			+ "where it.update_date > it.last_send_date "
			+ "or it.last_send_date is null", nativeQuery = true)
	public List<Tuple> findItems();
	
	public Optional<ItemB1> findByItemCode(String itemCode);

}
