package io.pivotal.cfapp.hints;

import java.util.Collections;
import java.util.List;

import org.springframework.nativex.hint.AccessBits;
import org.springframework.nativex.hint.TypeHint;
import org.springframework.nativex.type.HintDeclaration;
import org.springframework.nativex.type.NativeConfiguration;
import org.springframework.nativex.type.TypeSystem;

@TypeHint(
	typeNames = {
		"org.springframework.data.convert.ReadingConverter",
		"org.springframework.data.convert.WritingConverter"
	},
	access = AccessBits.ANNOTATION
)
public class SpringDataConverterHints implements NativeConfiguration {

    @Override
	public List<HintDeclaration> computeHints(TypeSystem typeSystem) {
		return Collections.emptyList();
	}
}
