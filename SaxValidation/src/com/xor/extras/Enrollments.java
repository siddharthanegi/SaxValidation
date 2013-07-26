package com.xor.extras;

import java.util.ArrayList;
import java.util.List;


public class Enrollments  {

		/**
	 * 
	 */
	
		private List<Enrollment> enrollment;
		public Enrollments(){
			enrollment=new ArrayList<Enrollment>();
		}

		public List<Enrollment> getEnrollment() {
			return enrollment;
		}

		public void setEnrollment(List<Enrollment> enrollment) {
			this.enrollment = enrollment;
		}
}


