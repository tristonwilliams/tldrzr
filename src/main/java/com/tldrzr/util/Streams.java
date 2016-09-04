/*
 *  
 *  TL;DRzr - A simple algorithmic summarizer
 *  Website: http://tldrzr.com
 *  Author: Saurav Mohapatra (mohaps@gmail.com)
 *  
 *  Copyright (c) 2016-2026, Saurav Mohapatra
 *  All rights reserved.
 *  
 *  
 *  
 *  Redistribution and use in source and binary forms, with or without modification, are permitted 
 *  provided that the following conditions are met:
 *  
 *  	a)  Redistributions of source code must retain the above copyright notice, 
 *  		this list of conditions and the following disclaimer.
 *  
 *  	b)  Redistributions in binary form must reproduce the above copyright notice, 
 *  		this list of conditions and the following disclaimer in the documentation 
 *  		and/or other materials provided with the distribution.
 *  	
 *  	c)  Neither the name of TL;DRzr nor the names of its contributors may be used 
 *  		to endorse or promote products derived from this software without specific 
 *  		prior written permission.
 *  
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, 
 *  BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT 
 *  SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL 
 *  DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) 
 *  HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 *  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.tldrzr.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public final class Streams {
	public static final void close(Closeable in) {
		try {
			if (in != null) {
				in.close();
			}
		} catch (IOException ex) {

		}
	}

	public static final InputStream openResource(String path) throws Exception {
		return Streams.class.getClassLoader().getResourceAsStream(path);
	}

	public static final Set<String> loadSet(String file) throws Exception {
		InputStream in = null;
		try {
			in = Streams.openResource(file);
			return loadSet(in);
		} finally {
			Streams.close(in);
		}
	}

	public static final Set<String> loadSet(InputStream in) throws Exception {
		BufferedReader lineReader = new BufferedReader(new InputStreamReader(in));
		Set<String> ret = new HashSet<String>();
		for (String line = lineReader.readLine(); line != null; line = lineReader.readLine()) {
			String trimmed = line.trim();
			if (Strings.isValidString(trimmed) && trimmed.charAt(0) != '#') {
				ret.add(trimmed.toLowerCase());
			}
		}
		return ret;
	}
}
