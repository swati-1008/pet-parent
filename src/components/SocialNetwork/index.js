import CreatePost from "./CreatePost/CreatePost";
import LeftSidePanel from "./LeftSidePanel/LeftPanel";
import Post from "./Post/Post";

export function SocialNetwork () {
    return (
        <div>
            <LeftSidePanel />
            <CreatePost />
            <Post />
        </div>
    )
}