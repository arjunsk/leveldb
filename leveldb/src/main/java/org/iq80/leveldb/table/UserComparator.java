/*
 * Copyright (C) 2011 the original author or authors.
 * See the notice.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.iq80.leveldb.table;

import org.iq80.leveldb.util.Slice;

import java.util.Comparator;

// todo this interface needs more thought
public interface UserComparator
        extends Comparator<Slice>
{
    String name();

    /*
    The FindShortestSeparator() function works as follows:

    o Determine the index of the first byte that differs between the
    previous key and the next key.
    o If that index is past the end of either key, then one key is a prefix
    of the other, so don't shorten the previous key.
    o Otherwise, check whether the first differing byte from the previous
    key can be incremented without being equal to the byte from the next
    key, and whether the byte from the previous key is not already at its
    maximum value of 0xFF. If so, shorten the previous key to include the
    shared prefix and that one incremented byte.
    o Otherwise, done shorten the previous key.


    ///Second : https://groups.google.com/g/leveldb/c/pbpfT0RYABU
    // This isn't optimal, but it matches the C++ Level-DB implementation, and
    // it's good enough. For example, if a is "1357" and b is "2", then the
    // optimal (i.e. shortest) result is appending "14", but we append "1357".

    */
    Slice findShortestSeparator(Slice start, Slice limit);

    Slice findShortSuccessor(Slice key);
}
