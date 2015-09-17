/*
 * classname:VStoreManage
 * 
 * Date:2015,9,12
 */
package client.shop;


import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import conn.common.Goods;

//for every good create a label and add the ActionListener
public class VStoreManage {
	private VStoreManage(){}
	private static VStoreManage instance =new VStoreManage();
	
	public static VStoreManage getVSM(){
		return instance;
	}
	
	//创建所有商品在主页面显示的标签，并且为每个标签添加鼠标响应
	public List<JLabel> getAllJLabel(ShopUser shopuser,final List <Goods> good){
		List<JLabel> jlabel = new ArrayList<>();
		for(int i = 0;i<good.size();i++){
			final JLabel jl= new JLabel(good.get(i).getGoodsName());
			ImageIcon pic = new ImageIcon(getClass().getResource(good.get(i).getGoodsImage()));
			pic.setImage(pic.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT));
			jl.setIcon(pic);
			jl.setPreferredSize((new Dimension(280,280)));
			final Goods newgood = good.get(i);
			final ShopUser user = shopuser;
			final GoodsInformation test =new GoodsInformation(user,newgood);
			
			jl.addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent e) {
					test.setVisible(true);	
						}
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					String str = "<html>"+"商品名称："+newgood.getGoodsName()+
							"<br>"+"商品价格："+newgood.getGoodsPrice()+"<br>"+"商品数量："+newgood.getGoodNumber()+"</html>";
					jl.setToolTipText(str);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
				}
				
			});
			jlabel.add(jl);
		}
		return jlabel;
	}
}
	
