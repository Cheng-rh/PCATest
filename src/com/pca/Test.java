package com.pca;

import Jama.Matrix;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Pca test=new Pca();
		//原始数据
		double[][] RawData=new double[][]
				{{40.4,24.7,7.2,6.1,8.3,8.7,2.442,20.0},
				{25.0,12.7,11.2,11.0,12.9,20.2,3.542,9.1},
				{13.2,3.3,3.9,4.3,4.4,5.5,0.578,3.6},
				{22.3,6.7,5.6,3.7,6.0,7.4,0.176,7.3},
				{34.3,11.8,7.1,7.1,8.0,8.9,1.726,27.5},
				{35.6,12.5,16.4,16.7,22.8,29.3,3.017,26.6},
				{22.0,7.8,9.9,10.2,12.6,17.6,0.847,10.6},
				{48.4,13.4,10.9,9.9,10.9,13.9,1.772,1.772},
				{40.6,19.1,19.8,19.0,29.7,39.6,2.449,35.8},
				{24.8,8.0,9.8,8.9,11.9,16.2,0.789,13.7},
				{12.5,9.7,4.2,4.2,4.6,6.5,0.874,3.9},
				{1.8,0.6,0.7,0.7,0.8,1.1,0.056,1.0},
				{32.3,13.9,9.4,8.3,9.8,13.3,2.126,17.1},
				{38.5,9.1,11.3,9.5,12.2,16.4,1.327,11.6},
				{26.2,10.1,5.6,15.6,7.7,30.1,0.126,25.9}};
		for(int i=0;i<RawData.length;i++){
			for(int j=0;j<RawData[0].length;j++){
				System.out.print(RawData[i][j]+" ");
			}
			System.out.println();
		}
		//标准化的数据
//		double[][] Standard=test.Standardlizer(RawData);
//		double[][] Assosiation=test.CoefficientOfAssociation(Standard);
//		int n=RawData.length;
//		int p=RawData[0].length;
//		for(int i=0;i<p;i++){
//			for(int j=0;j<p;j++){
//				System.out.print(Assosiation[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
//		double[][] FlagValue=test.FlagValue(Assosiation);
		double[][] FlagValue=test.FlagValue(RawData);
		int n=RawData.length;
		int p=RawData[0].length;
		for(int i=0;i<p;i++){
			for(int j=0;j<p;j++){
				System.out.print(FlagValue[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		
		double[][] FlagVector=test.FlagVector(RawData);
		for(int i=0;i<p;i++){
			for(int j=0;j<p;j++){
				System.out.print(FlagVector[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		
		int[] xuan=test.SelectPrincipalComponent(FlagValue);
		for(int i=0;i<xuan.length;i++){
			
			System.out.print(xuan[i]+" ");
		
		}
		System.out.println();
		double[][] result=test.PrincipalComponent(FlagVector, xuan);
		for(int i=0;i<p;i++){
			for(int j=0;j<xuan.length;j++){
				System.out.print("主成分"+result[i][j]+" ");
			}
			System.out.println();
		}


		Matrix A=new Matrix(RawData);
		Matrix B=new Matrix(result);
		Matrix C=A.times(B);
		C.print(4, 2);
		double[][] D=C.getArray();
		double[] E=new double[RawData.length];
		for(int i=0;i<D.length;i++){
			double temp=0;
			for(int j=0;j<D[0].length;j++){
				temp+=D[i][j];
			}
			E[i]=temp;
		}
		System.out.println();
		for(int i=0;i<D.length;i++){		
				System.out.println(E[i]);
		}
		
	}

}
