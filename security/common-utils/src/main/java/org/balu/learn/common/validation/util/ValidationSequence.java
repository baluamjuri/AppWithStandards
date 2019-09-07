package org.balu.learn.common.validation.util;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * It is used with @Validated to tell the sequence<br/>
 * {@link Default} validations performs first then {@link Sequence.Step1}
 * validations and then {@link Sequence.Step2} and so on.
 * Usage: @Validated(ValidationSequence.class)
 * 
 * @author amjuribv
 *
 */
@GroupSequence({ Default.class, Sequence.Step1.class, Sequence.Step2.class, Sequence.Step3.class })
public interface ValidationSequence {
}