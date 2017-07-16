package com.pca;
import java.util.ArrayList;

import Jama.Matrix;
public class PCATest {
	public  double convert(double value){
		long l1 = Math.round(value*100000); //��������
		double ret = (double)l1/100000.0; //ע��:ʹ�� 100.0 ������ 100
		return ret;
	}
	public void analyse(double [][]data){
		  double[][]ch = new double[data[0].length][data[0].length];
		  double[]average = new double[data[0].length];
		  for(int i=0;i<average.length;i++){
			  double sum=0.0;
			  for(int j=0;j<data.length;j++)sum+=data[j][i];
			  sum= sum/(double)(data.length);
			  average[i]=sum;
		  }
		  for(int i=0;i<ch.length;i++){  //�������ϵ������
			  for(int j=0;j<ch[i].length;j++){
				  double temp1=0.0;
				  double temp2=0.0;
				  double sumx=0.0;
				  double sumy=0.0;
				  for(int k=0;k<data.length;k++){
					  temp1+=(data[k][i]-average[i])*(data[k][j]-average[j]);	
					  sumx+=(data[k][i]-average[i])*(data[k][i]-average[i]);
					  sumy+=(data[k][j]-average[j])*(data[k][j]-average[j]);
				  }
				  temp2=Math.sqrt(sumx*sumy);
				  ch[i][j]=temp1/temp2;
				  //ch[i][j]=convert(temp2);
			  }
		  }
		  //for(int i =0;i<ch.length;i++){
		//	  for(int j =0;j<ch[i].length;j++){
		////		  System.out.print(ch[i][j]+" ");
		//	  }
		//	  System.out.println();
		//  }
		  Matrix B = new Matrix(ch);
	      System.out.println("����ֵ����");
		  B.eig().getD().print(4,4);//����ֵ������С�������λ����
		  System.out.println("������������");
		  B.eig().getV().print(4,4);//��������������С�������λ����
	      double [][]D = new double[data[0].length][2];
	      double sum = 0.0;//����ֵ֮��
	      for(int i=0;i<D.length;i++){
	    	  D[i][0] = B.eig().getD().get(i,i);
	    	  sum+=D[i][0];
	    	  D[i][1] = i;
	      }
	      for(int i=0;i<D.length-1;i++){
	    	  for(int j=i+1;j<D.length;j++){
	    		  if(D[j][0]>D[i][0]){
	    			  double temp = D[j][0];
	    			  D[j][0] = D[i][0];
	    			  D[i][0] = temp;
	    			  temp = D[j][1];
	    			  D[j][1] = D[i][1];
	    			  D[i][1] = temp;
	    		  }
	    	  }
	      }
	      
	      ArrayList<Integer> save = new ArrayList<Integer>();
	      double v = 0.0;
	      for(int i=0;i<D.length;i++){
	    	  if(v/sum<0.9){
	    		  v+=D[i][0];
		    	  //System.out.println(D[i][0]);
		    	  save.add((int)D[i][1]);
	    	  }
	    	  
	      }
	      for(int i=0;i<save.size();i++){
	    	  System.out.println("��"+Integer.toString(i+1)+"���ɷ�:");
	    	  for(int j=0;j<ch.length;j++){
	    		  if(j==0){
	    			  System.out.print(B.eig().getV().get(j,save.get(i))+"X"+Integer.toString(j+1));
	    		  }
	    		  else{
	    			  if(B.eig().getV().get(j, save.get(i))>=0)
	    			  System.out.print("+"+B.eig().getV().get(j, save.get(i))+"X"+Integer.toString(j+1));
	    			  else
	    		      System.out.print(B.eig().getV().get(j, save.get(i))+"X"+Integer.toString(j+1));
	    		  }
	    	  }
	    	  System.out.println();
	      }
	      //System.out.println(save.size());
	}
	public static void main(String args[]){
		  double [][]data={{149.3,4.2,108.1},
		               {161.2,4.1,114.8},
		               {171.5,3.1,123.2},
		               {175.5,3.1,126.9},
		               {180.8,1.1,132.1},
		               {190.7,2.2,137.7},
		               {202.1,2.1,146.0},
		               {212.4,5.6,154.1},
		               {226.1,5.0,162.3},
		               {231.9,5.1,164.3},
		               {239.0,0.7,167.6}};
	      PCATest p = new PCATest();
	      p.analyse(data);
	      //System.out.println(B.eig().getD().get(0,0));
	}

}
