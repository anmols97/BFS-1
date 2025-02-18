//Time Complexity: V+E
//Space Complexity: O(1)
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int [numCourses];
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for(int[] edge : prerequisites){
            indegrees[edge[0]]++;
            if(!map.containsKey(edge[1])){
                map.put(edge[1], new ArrayList<>());
            }
            map.get(edge[1]).add(edge[0]);
        }
        //next step
        int count = 0;
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i< numCourses; i++){
            if(indegrees[i]==0){
                q.add(i);
                count++;
            }
        }
        if(q.isEmpty()) return false;
        while(!q.isEmpty()){
            int curr = q.poll();
            //Reduce the dependency of the courses which were dependent on curr course
            List<Integer> children = map.get(curr); 
            if(children!= null){
                for(int child: children){
                    indegrees[child]--;
                    if(indegrees[child] == 0){ //if it becomes independent
                        q.add(child);
                        count++;
                    }

                }
            }        
        }
        if(count == numCourses) return true;
        return false;
    }
}