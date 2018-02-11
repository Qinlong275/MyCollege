package com.qinlong.algsTest1;

public class MathUtil {
	/**
	 * �����˫����������ֵ�����ֵ
	 * 
	 * @param inputData
	 *            ������������
	 * @return ������,�������ֵ���Ϸ�������Ϊ-1
	 */
	public static double getMax(double[] inputData) {
		if (inputData == null || inputData.length == 0)
			return -1;
		int len = inputData.length;
		double max = inputData[0];
		for (int i = 0; i < len; i++) {
			if (max < inputData[i])
				max = inputData[i];
		}
		return max;
	}

	/**
	 * �������˫����������ֵ����Сֵ
	 * 
	 * @param inputData
	 *            ������������
	 * @return ������,�������ֵ���Ϸ�������Ϊ-1
	 */
	public static double getMin(double[] inputData) {
		if (inputData == null || inputData.length == 0)
			return -1;
		int len = inputData.length;
		double min = inputData[0];
		for (int i = 0; i < len; i++) {
			if (min > inputData[i])
				min = inputData[i];
		}
		return min;
	}

	/**
	 * �����˫����������ֵ�ĺ�
	 * 
	 * @param inputData
	 *            ������������
	 * @return ������
	 */
	public static double getSum(double[] inputData) {
		if (inputData == null || inputData.length == 0)
			return -1;
		int len = inputData.length;
		double sum = 0;
		for (int i = 0; i < len; i++) {
			sum = sum + inputData[i];
		}
		return sum;
	}
	
	public static long getSum(long[] inputData) {
		if (inputData == null || inputData.length == 0)
			return -1;
		int len = inputData.length;
		long sum = 0;
		for (int i = 0; i < len; i++) {
			sum = sum + inputData[i];
		}
		return sum;
	}

	/**
	 * �����˫����������ֵ����Ŀ
	 * 
	 * @param input
	 *            Data ������������
	 * @return ������
	 */
	public static int getCount(double[] inputData) {
		if (inputData == null)
			return -1;
		return inputData.length;
	}

	/**
	 * �����˫����������ֵ��ƽ��ֵ
	 * 
	 * @param inputData
	 *            ������������
	 * @return ������
	 */
	public static double getAverage(double[] inputData) {
		if (inputData == null || inputData.length == 0)
			return -1;
		int len = inputData.length;
		double result;
		result = getSum(inputData) / len;

		return result;
	}
	
	public static long getAverage(long[] inputData) {
		if (inputData == null || inputData.length == 0)
			return -1;
		int len = inputData.length;
		long result;
		result = getSum(inputData) / len;

		return result;
	}

	/**
	 * �����˫����������ֵ��ƽ����
	 * 
	 * @param inputData
	 *            ������������
	 * @return ������
	 */
	public static double getSquareSum(double[] inputData) {
		if (inputData == null || inputData.length == 0)
			return -1;
		int len = inputData.length;
		double sqrsum = 0.0;
		for (int i = 0; i < len; i++) {
			sqrsum = sqrsum + inputData[i] * inputData[i];
		}

		return sqrsum;
	}

	/**
	 * �����˫����������ֵ�ķ���
	 * 
	 * @param inputData
	 *            ������������
	 * @return ������
	 */
	public static double getVariance(double[] inputData) {
		int count = getCount(inputData);
		double sqrsum = getSquareSum(inputData);
		double average = getAverage(inputData);
		double result;
		result = (sqrsum - count * average * average) / count;
		return result;
	}

	/**
	 * �����˫����������ֵ�ı�׼��
	 * 
	 * @param inputData
	 *            ������������
	 * @return ������
	 */
	public static double getStandardDiviation(double[] inputData) {
		double result;
		// ����ֵ������Ҫ
		result = Math.sqrt(Math.abs(getVariance(inputData)));

		return result;
	}

}
