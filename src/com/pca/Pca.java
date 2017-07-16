package com.pca;
import java.math.*;

import a2.ab;
import Jama.Matrix;
public class Pca {
	//将原始数据标准化
	public double[][] Standardlizer(double[][] x){
		int n=x.length;		//二维矩阵的行号
		int p=x[0].length;	//二维矩阵的列号
		double[] average=new double[p];	//每一列的平均值
		double[][] result=new double[n][p];	//标准化后的向量
		double[] var=new double[p];      //方差
		//取得每一列的平均值
		for(int k=0;k<p;k++){
			double temp=0;
			for(int i=0;i<n;i++){
				temp+=x[i][k];
			}
			average[k]=temp/n;
		}
		//取得方差
		for(int k=0;k<p;k++){
			double temp=0;
			for(int i=0;i<n;i++){
				temp+=(x[i][k]-average[k])*(x[i][k]-average[k]);
			}
			var[k]=temp/(n-1);
		}
		//获得标准化的矩阵
		for(int i=0;i<n;i++){
			for(int j=0;j<p;j++){
				result[i][j]=(double) ((x[i][j]-average[j])/Math.sqrt(var[j]));
			}
		}
		return result;
		
	}
	//计算样本相关系数矩阵
	public double[][] CoefficientOfAssociation(double[][] x){
		int n=x.length;		//二维矩阵的行号
		int p=x[0].length;	//二维矩阵的列号
		double[][] result=new double[p][p];//相关系数矩阵
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
	//计算相关系数矩阵的特征值
	public double[][] FlagValue(double[][] x){
		//定义一个矩阵
	      Matrix A = new Matrix(x);
	      //由特征值组成的对角矩阵
	     Matrix B=A.eig().getD();
	     double[][] result=B.getArray();
	     return result;
	
		
	}
	//计算相关系数矩阵的特征向量
	public double[][] FlagVector(double[][] x){
		//定义一个矩阵
	      Matrix A = new Matrix(x);
	      //由特征向量组成的对角矩阵
	     Matrix B=A.eig().getV();
	     double[][] result=B.getArray();
	
	     return result;
		
		
	}
	//假设阈值是90%，选取最大的前几个
	public int[] SelectPrincipalComponent(double[][] x){
		int n=x.length;		//二维矩阵的行号,列号
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
	//取得主成分
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
