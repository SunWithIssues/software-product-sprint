// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Collection;
import java.util.HashSet;


public final class FindMeetingQuery {

  private Collection<TimeRange> collect = new ArrayList<TimeRange>();

  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
    // throw new UnsupportedOperationException("TODO: Implement this method.");

    // Collection<TimeRange> collect = new ArrayList();
    //  = new Collection<TimeRange>();
    long duration = request.getDuration();                 // Duration of the meeting, request
    Collection<String> attendees = request.getAttendees(); // Attendees of the meeting, request

    // For any request, where the duration is greater than 24 hours. No 
    //  times can be given.
    if (duration > TimeRange.WHOLE_DAY.duration()){
        return collect;
    }
    // For any request, where no events are schedule. The whole day can be
    //  used.
    else if(events.isEmpty()){ 
        return Arrays.asList(TimeRange.WHOLE_DAY);
    }

    collect.add(TimeRange.WHOLE_DAY);
    // collect.add(TimeRange.fromStartEnd(0,60,false));

    for(Event e: events){
        TimeRange range = e.getWhen();
        for(TimeRange r: collect){
            if(r.contains(range)){
                collect.add(TimeRange.fromStartEnd(r.start(), range.start(), false));
                collect.add(TimeRange.fromStartEnd(range.end(), r.end(), false));  
                collect.remove(r);
                break;
            }
            
        }
    }

    return collect;
  }
}
