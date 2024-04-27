import avatar from '../../../assets/images/dp1.jpeg';
import content from '../../../assets/images/content1.jpeg';

const postList = [
    {
        userImage: avatar, 
        userHandle: 'test123', 
        body: content, 
        createdAt: new Date().toISOString(), 
        liked: true, 
        likeCount: 8712, 
        commentCount: 125, 
        saved: false, 
        savedCount: 500, 
    }, 
    {
        userImage: avatar, 
        userHandle: 'test456', 
        body: content, 
        createdAt: new Date().toISOString(), 
        liked: false, 
        likeCount: 200, 
        commentCount: 82911, 
        saved: true, 
        savedCount: 7361, 
    }, 
    {
        userImage: avatar, 
        userHandle: 'test789', 
        body: content, 
        createdAt: new Date().toISOString(), 
        liked: false, 
        likeCount: 103837, 
        commentCount: 732, 
        saved: false, 
        savedCount: 52711, 
    }, 
    {
        userImage: avatar, 
        userHandle: 'test000', 
        body: content, 
        createdAt: new Date().toISOString(), 
        liked: true, 
        likeCount: 729, 
        commentCount: 13, 
        saved: true, 
        savedCount: 88, 
    }
];

export default postList;