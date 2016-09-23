
import java.util.Scanner;

public class ControlPoints {
	
	public static void main(String[] args) {
		int n;
		int x0;
		int[] points;
		int dist;
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		x0 = input.nextInt();
		points = new int[n];
		for(int i = 0; i < n; i++) {
			points[i] = input.nextInt();
		}
		mergeSort(points, n);
		dist = calculateDists(points, x0, n);
		System.out.println(dist);
	}

	public static int calculateDists(int[] x, int x0, int n) {
		int dist = 0;
		int pointCount = 0;
		int lp = searchPosition(x, x0, n);
		int rp = (lp < n-1) ? lp + 1 : lp;
		boolean[] visit = new boolean[n];
		while (pointCount != n-1) {
			while(lp > 0) {
				if (!visit[lp]) {break;}
				lp--;
			}
			while(rp < n-1) {
				if (!visit[rp]) {break;}
				rp++;
			}
			if (((x[lp] + x[rp]) > (2 * x0) || visit[rp]) & !visit[lp]) {
				dist += x0 - x[lp];
				x0 = x[lp];
				visit[lp] = true;
			} else {
				dist += x[rp] - x0;
				x0 = x[rp];
				visit[rp] = true;
			}
			pointCount++;
		}
		return dist;
	}
	
	public static void mergeSort(int[] x, int n) {
		for (int k = 1; k < n; k *= 2) {
			for(int i = 0; i + k < n; i += 2*k) {
				merge(x, i, i + k, n);
			}
		}
	}
	
	public static void merge(int[] x, int in1, int in2, int n) {
		int l = in2 - in1; 
		int l1 = l;
		int l2 = (in2 + l < n) ? l : n - in2;
		int[] y = new int[l1 + l2];
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < l1 && j < l2) {
			if (x[in1 + i] < x[in2 + j]) {
				y[k++] = x[in1 + i++];
			} else {
				y[k++] = x[in2 + j++];
			}
		}
		while (i < l1) {
			y[k++] = x[in1 + i++];
		}
		while (j < l2) {
			y[k++] = x[in2 + j++];
		}
		for(int z = 0; z < k; z++) {
			x[in1 + z] = y[z];
		}
	}
	
	public static int searchPosition(int[] x, int x0, int n) {
		int l = 0;
		int r = n-1;
		while (l + 1 < r) {
			int mid = (l + r) >> 1;
			if (x[mid] > x0) { 
				r = mid;
			} else {
				l = mid;
			}
		}
		return l;
	}	
}

