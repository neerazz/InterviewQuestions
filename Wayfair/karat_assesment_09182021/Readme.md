# Technical Questions
- Question 1:
    - Given two tables, users, and friendship.
  - User:
    - User_id,
    - Name,
    - Date
  - Friendship:
    - Friend_id
    - User_id1
    - User_id2
    - Date
  - Find the number of friends for a user id and the name.
  - >  Select name, 
      (select count(*) from friendship where user_id1 = required_user_id || user_id2 = required_user_id) as count
    from user where user_id = required_user_id;

- Question 2:
>	Given a doc sign system, where each doc needs to be signed multiple times. A table is present to keep track of all the completed documents. After a doc is signed a notification needs to be sent. After each notification is sent the file id is stored in a flat file. You have noticed that the notification system had crashed, how do you know for which all documents the notification is missed.
>	Get the set of docs that is completely signed from db, read through the file and remove the doc for which notification is sent.
>	The remaining set of documents are the missing documents.

- Question 3: 
>	You are trying to create a clone of Google Docs. The system is designed in such a way that, many users can edit a document and one document can only be stored on a server. You have designed the Load balancer to do a round robin distribution of load. Where a document will be stored in one server and all the user activities will be handled by that server.
>	Do you think that round-robin approach for a load balance is correct, if not why and what would be your approach.
>	I don’t think round-robin is a better approach for this type of problem. Because there is a high possibility of celebrity problem to happen. The allocation should be done based on how many documents and users each server is handling, and the next document should be assigned to a server that has least number of documents and users.

- Questions 4: Strict consistency vs Eventual Consistency
	• An api with an SLA of 50 ms, which
>		○ Eventual Consistency
	• An auditing system where every user clicks are stored, and a system reports the statistics of the site.
>		○ Eventual Consistency
	• A financial system where money deposit and withdrawal is done.
>		○ Strict Consistency

- Question 5: 
	Find the throughput of a request after flowing through set up steps in a pipeline.
>	Given the relation of the pipeline and the requests processes per second. 

# Algorithm Questions
[Has CommonAncestor](HasCommonAncestor.java)
https://github.com/neerazz/FAANG/blob/f803bdd30c9d44bfde3cc8aacd64c536327d845e/Algorithms/Neeraj/algorithum/dfs-bfs/HasCommonAncestor.java
