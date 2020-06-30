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


public final class FindMeetingQuery {

  private Collection<TimeRange> collect = new ArrayList<TimeRange>();
  private boolean flag = true;

  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {

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

    //O(n*m)
    for(Event e: events){
        TimeRange range = e.getWhen();
        Collection<String> people = e.getAttendees();
        
        // Check if event people match the attendees request
        for (String a: attendees){
            if(people.contains(a)){
                flag = true;
                break;
            }
            flag=false;
        }

        if(flag){
            for(TimeRange r: collect){
                // IMPORTANT: Should I check if the range==r? Would this create two TimeRange objects
                    // unnecessarily in this case?
                if(r.contains(range)){
                    if(r.start() == range.start()){
                        collect.add(TimeRange.fromStartEnd(range.end(), r.end(), false));
                    }
                    else if(r.end() == range.end()){
                        collect.add(TimeRange.fromStartEnd(r.start(), range.start(), false));
                    }
                    else{
                        collect.add(TimeRange.fromStartEnd(r.start(), range.start(), false));
                        collect.add(TimeRange.fromStartEnd(range.end(), r.end(), false)); 
                    }
                    collect.remove(r);
                    break; // If it is contained, then no other free time is a concern.
                }
                // Since the range for the event can overlap multiple free times, we must
                    // check all free times. Hence it does not end with a break statement.
                else if(r.overlaps(range)){
                    if(r.contains(range.start())){
                        collect.add(TimeRange.fromStartEnd(r.start(), range.start(), false));
                    }
                    else{
                        collect.add(TimeRange.fromStartEnd(range.end(), r.end(), false));
                    }
                    collect.remove(r);
                }
                
            }
        } // End of Flag check
    }// End of event loop


    // TODO: This could be strategically placed in the loop before.
    // for(TimeRange r: collect){
    //     if(duration > (long)r.duration()){
    //         collect.remove(r);
    //     }
    // }

    return collect;
  }


}
