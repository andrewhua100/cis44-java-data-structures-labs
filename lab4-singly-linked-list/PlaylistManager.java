import java.util.Objects;

public class PlaylistManager {
    public static class Song {
        String title;
        String artist;
        //constructor with title and artist
        public Song(String title, String artist){
            this.title = title;
            this.artist = artist;
        }
    }

    public static class Playlist {
        private static class Node {
            Song song;
            Node next;
            //constructor with song and next pointer
            public Node(Song song, Node next){
                this.song = song;
                this.next = next;
            }
        }

        private Node head;//head of the list
        private Node tail;//tail of the list
        private Node currentNode;//currentNode used for tracking which song is playing
        private int size; //size of the playlist

        //empty playlist constructor
        public Playlist() {
            this.head = null;
            this.tail = null;
            this.currentNode = null;
            this.size = 0;
        }

        public void addSong(Song song) {
            Node newnode = new Node(song, null); //creates a new node with the song and the null next pointer(end of the list)
            if(size==0){//if no items are in the list
                //head and tail both become the newnode
                tail = newnode;
                head = tail;
            }
            else{
                //current tail's next pointer points to the newnode, and tail is replaced with newnode
                tail.next = newnode;
                tail = newnode;
            }
            size++;//size increases by 1
        }

        public void removeSong(String title) {
            if(head.song.title.equals(title)){//if the song to be removed is the head, replace the head with the song after it
                head = head.next;
                size--;
            }
            else{
                Node curr = head;//node to track the current node in iteration
                while(true){
                    if(Objects.equals(curr.next.song.title, title)){//if the current node's next node has the same title of the song we want to remove
                        System.out.println("Removed " + title + " from the playlist");
                        if(curr.next == tail){ //if the current node's next node is the tail we set the tail to the current node and make the next node null
                            tail = curr;
                            tail.next = null;
                            size--;
                            break;
                        }
                        // otherwise we set the next node of the current node to the next node of the one we just removed
                        curr.next = curr.next.next;
                        size--;
                        break;
                    }
                    //if the titles don't match we move on to the next node
                    curr = curr.next;
                }
            }
        }
        //method to play the next song in the playlist
        public void playNext() {
            if(currentNode == null){ //if the playlist still hasn't started playing yet
                //set currentNode to the head of the list and print the play message
                currentNode = head;
                System.out.println("Now playing " + currentNode.song.title + " by " + currentNode.song.artist);
            }
            else{
                if(currentNode == tail){//if currentNode is the tail, loop back around to the head and print the play message
                    currentNode = head;
                    System.out.println("Now playing " + currentNode.song.title + " by " + currentNode.song.artist);
                }
                else { // otherwise move on to the next song and print the play message
                    currentNode = currentNode.next;
                    System.out.println("Now playing " + currentNode.song.title + " by " + currentNode.song.artist);
                }

            }
        }
        //method to display all songs in the playlist
        public void displayPlaylist() {
            Node curr = head; //curr node for iterating the list
            while(true){
                if(curr==tail){ //if curr is the tail it is the last song so break after printing it
                    System.out.print(curr.song.title + " by " + curr.song.artist);
                    break;
                }
                //otherwise print the song and update curr to the next song
                System.out.print(curr.song.title + " by " + curr.song.artist + ", ");
                curr = curr.next;
            }
            System.out.println();
            System.out.println("Size of the playlist: " + size);
        }
    }
    public static void main(String[] args){
        Playlist play = new Playlist(); //new playlists

        //songs
        Song shapeOfYou = new Song("Shape of You", "Ed Sheeran");
        Song Atlantis = new Song("Atlantis", "Seafret");
        Song intentions = new Song("intentions", "starfall");
        Song violet = new Song("The Color Violet", "Tory Lanez");

        //adding songs to the playlist
        play.addSong(shapeOfYou);
        play.addSong(Atlantis);
        play.addSong(intentions);
        play.addSong(violet);

        play.displayPlaylist();

        //removing a song
        play.removeSong("Atlantis");

        //playing songs in the playlist
        play.playNext();
        play.playNext();
        play.playNext();
        play.playNext();

        play.displayPlaylist();
    }
}
