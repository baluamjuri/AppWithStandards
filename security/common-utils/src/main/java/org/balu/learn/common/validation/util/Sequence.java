package org.balu.learn.common.validation.util;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

/**
 * It is used to tell the validation group sequence. <br>
 * Ex: <b> @GroupSequence({Default.class, Sequence.Step1.class,
 * Sequence.Step2.class, Sequence.Step3.class}) </b> <br/>
 * {@link Default} validations performs first then {@link Sequence.Step1}
 * validations and then {@link Sequence.Step2} and so on.
 * 
 * @author amjuribv
 * @see ValidationSequence
 */
public class Sequence {
	public static interface Step1 {
	}

	public static interface Step2 {
	}

	public static interface Step3 {
	}
}
