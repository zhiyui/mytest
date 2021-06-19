package com.changgou.goods.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 实体类
 * @author Administrator
 *
 */
@Table(name="tb_category")
public class Category {

	@Id
	private Integer id;//分类ID


	
	private String name;//分类名称
	@Column(name = "goods_num")
	private Integer goodsNum;//商品数量
	@Column(name = "is_show")
	private String isShow;//是否显示
	@Column(name = "is_menu")
	private String isMenu;//是否导航
	private Integer seq;//排序
	@Column(name = "parent_id")
	private Integer parentId;//上级ID
	@Column(name = "template_id")
	private Integer templateId;//模板ID


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public String getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(String isMenu) {
		this.isMenu = isMenu;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
}
