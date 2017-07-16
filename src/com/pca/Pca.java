package com.pca;
import java.math.*;

import a2.ab;
import Jama.Matrix;
public class Pca {
	//��ԭʼ���ݱ�׼��
	public double[][] Standardlizer(double[][] x){
		int n=x.length;		//��ά������к�
		int p=x[0].length;	//��ά������к�
		double[] average=new double[p];	//ÿһ�е�ƽ��ֵ
		double[][] result=new double[n][p];	//��׼���������
		double[] var=new double[p];      //����
		//ȡ��ÿһ�е�ƽ��ֵ
		for(int k=0;k<p;k++){
			double temp=0;
			for(int i=0;i<n;i++){
				temp+=x[i][k];
			}
			average[k]=temp/n;
		}
		//ȡ�÷���
		for(int k=0;k<p;k++){
			double temp=0;
			for(int i=0;i<n;i++){
				temp+=(x[i][k]-average[k])*(x[i][k]-average[k]);
			}
			var[k]=temp/(n-1);
		}
		//��ñ�׼���ľ���
		for(int i=0;i<n;i++){
			for(int j=0;j<p;j++){
				result[i][j]=(double) ((x[i][j]-average[j])/Math.sqrt(var[j]));
			}
		}
		return result;
		
	}
	//�����������ϵ������
	public double[][] CoefficientOfAssociation(double[][] x){
		int n=x.length;		//��ά������к�
		int p=x[0].length;	//��ά������к�
		double[][] result=new double[p][p];//���ϵ������
		for(int i=0;i<p;i++){
			for(int j=0;j<p;j++){
				double temp=0;
				for(int k=0;k<n;k++){
					temp+=x[k][i]*x[k][j];
				}
				result[i][j]=temp/(n-1);
			}
		}
		return result;
		
	}
	//�������ϵ�����������ֵ
	public double[][] FlagValue(double[][] x){
		//����һ������
	      Matrix A = new Matrix(x);
	      //������ֵ��ɵĶԽǾ���
	     Matrix B=A.eig().getD();
	     double[][] result=B.getArray();
	     return result;
	
		
	}
	//�������ϵ���������������
	public double[][] FlagVector(double[][] x){
		//����һ������
	      Matrix A = new Matrix(x);
	      //������������ɵĶԽǾ���
	     Matrix B=A.eig().getV();
	     double[][] result=B.getArray();
	
	     return result;
		
		
	}
	//������ֵ��90%��ѡȡ����ǰ����
	public int[] SelectPrincipalComponent(double[][] x){
		int n=x.length;		//��ά������к�,�к�
		double[] a = new double[n];
		int[] result = new int[n];
		int k=0;
		double temp = 0;
		int m=0;
		double total=0;
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(i==j){
					a[k]=x[i][j];
				}
			}
			k++;
		}
		double[] temp1=new double[a.length];
		
		System.arraycopy(a, 0, temp1, 0, a.length);
		for(int i=0;i<n;i++){
			temp=temp1[i];
			for(int j=i;j<n;j++){
				if(temp<=temp1[j]){
					temp=temp1[j];
					temp1[j]=temp1[i];
				}
				
				temp1[i]=temp;
			}
		}
		for(int i=0;i<n;i++){
			temp=a[i];
			for(int j=0;j<n;j++){
				if(a[j]>=temp){
					temp=a[j];
					
					k=j;
					
				}
				result[m]=k;
				
			}
			a[k]=-1000;
			
			m++;
		}
		for(int i=0;i<n;i++){
			total+=temp1[i];
		}
		int sum=1;
		temp=temp1[0];
		for(int i=0;i<n;i++){
			if(temp/total<=0.9){
				temp+=temp1[i+1];
				sum++;
			}
		}
		int[] end=new int[sum];
		System.arraycopy(result, 0, end, 0, sum);
		
		return end;
		
	}
	//ȡ�����ɷ�
	public double[][] PrincipalComponent(double[][] x,int[] y){
		int n=x.length;
		double[][] Result=new double[n][y.length];
		int k=y.length-1;
		for(int i=0;i<y.length;i++){
			for(int j=0;j<n;j++){
				Result[j][i]=x[j][y[k]];
			}
			k--;
		}
		return Result;
		
	}
	
}
