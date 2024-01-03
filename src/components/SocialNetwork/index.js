import CreatePost from "./CreatePost/CreatePost";
import LeftSidePanel from "./LeftSidePanel/LeftPanel";

export function SocialNetwork () {
    return (
        <div>
            <LeftSidePanel />
            <CreatePost />
        </div>
    )
}