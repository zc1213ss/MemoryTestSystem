import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ShowRecordDialog extends JDialog implements ActionListener{
	File gradeFile;
	JButton clear;
	JTextArea showArea=null;
	TreeSet<People>treeSet;//此类实现 Set 接口，该接口由 TreeMap 实例支持。此类保证排序后的 set 按照升序排列元素，根据使用的
	//构造方法不同，可能会按照元素的自然顺序 进行排序（参见 Comparable），或按照在创建 set 时所提供的比较器进行排序。
	public ShowRecordDialog(){
		treeSet=new TreeSet<People>();
		showArea=new JTextArea(6,4);
		showArea.setFont(new Font("楷书",Font.BOLD,20));
		clear=new JButton("清空排行榜");
		clear.addActionListener(this);
		add(new JScrollPane(showArea),BorderLayout.CENTER);
		add(clear,BorderLayout.SOUTH);
		setBounds(100,100,320,185);
		setModal(true);//有模式对话框，当前只有此对话框是被激活的
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				setVisible(true);
			}
		});
	}
	public void setGradeFile(File f){
		gradeFile=f;
		setTitle(f.getName());
	}
	public void showRecord(){
		showArea.setText(null);
		treeSet.clear();
		try{
			RandomAccessFile in=new RandomAccessFile(gradeFile,"rw");
			long fileLength=in.length();
			long readPosition=0;
			while(readPosition<fileLength){
				String name=in.readUTF();//从此文件读取一个字符串。
				int time=in.readInt();//从此文件读取一个有符号的 32 位整数。
				readPosition=in.getFilePointer();
				People people=new People(name,time);
				treeSet.add(people);	
			}
			in.close();
			Iterator<People>iter=treeSet.iterator();//迭代器允许调用方利用定义良好的语义在迭代期间从迭代器所指向的
			//集合移除元素。 
			while(iter.hasNext()){
				People p=iter.next();
				showArea.append("姓名:"+p.getName()+",成绩："+p.getTime()+"秒");
				showArea.append("\n");
			}	
		}
		catch(IOException exp){System.out.println(exp);}
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==clear){
			try{
				File f=gradeFile.getAbsoluteFile();//返回抽象路径名的绝对路径名形式。
				gradeFile.delete();
				f.createNewFile();
				showArea.setText("排行榜被清空");
			}
			catch(Exception ee){}
		}
	}
}
