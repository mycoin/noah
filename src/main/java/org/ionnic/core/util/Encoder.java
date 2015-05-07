package org.ionnic.core.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class Encoder {

	private static char[] charByte = new char[65536];

	static {
		CharsetEncoder ce = Charset.forName("GBK").newEncoder();
		char[] ca = new char[1];
		byte[] ba = new byte[2];
		CharBuffer cb = CharBuffer.wrap(ca);
		ByteBuffer bb = ByteBuffer.wrap(ba);
		for (int i = 0; i < 128; i++) {
			charByte[i] = (char) i;
		}
		for (int i = 128; i < 65536; i++) {
			ca[0] = (char) i;
			ce.encode(cb, bb, false);
			cb.flip();
			bb.flip();
			if (bb.limit() == 1) {
				int v = ba[0] & 0xFF;
				charByte[i] = (char) v;
			} else {
				int v1 = ba[0] & 0xFF;
				int v2 = ba[1] & 0xFF;
				charByte[i] = (char) ((v1 << 8) | v2);
			}
		}
	}

	/**
	 * @param in
	 * @param out
	 */
	public void gbk(CharBuffer in, ByteBuffer out) {
		char[] ca = in.array();
		byte[] ba = out.array();
		int len = in.limit();
		int i = 0;
		int j = 0;
		while (i < len && ca[i] < 0x80) {
			ba[j++] = (byte) ca[i++];
		}
		while (i < len) {
			int c = charByte[ca[i]];
			if (c < 0x100) {
				ba[j++] = (byte) c;
			} else {
				ba[j++] = (byte) (c >> 8);
				ba[j++] = (byte) c;
			}
			i++;
		}
		out.position(j);
	}

	/**
	 * @param in
	 * @param out
	 */
	public void utf8(CharBuffer in, ByteBuffer out) {
		char[] ca = in.array();
		byte[] ba = out.array();
		int len = in.limit();
		int i = 0;
		int j = 0;
		while (i < len && ca[i] < 0x80) {
			ba[j++] = (byte) ca[i++];
		}
		while (i < len) {
			int c = ca[i];
			if (c < 0x80) {
				ba[j++] = (byte) c;
			} else if (c < 0x800) {
				ba[j++] = (byte) ((c & 0x7C0 | 0x3000) >> 6);
				ba[j++] = (byte) (c & 0x3F | 0x80);
			} else {
				ba[j++] = (byte) ((c & 0xF000 | 0xE0000) >> 12);
				ba[j++] = (byte) ((c & 0xFC0 | 0x2000) >> 6);
				ba[j++] = (byte) (c & 0x3F | 0x80);
			}
			i++;
		}
		out.position(j);
	}
}
