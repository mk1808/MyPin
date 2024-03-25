
interface Notification {
    id: string,
    ownerId: string,
    userId: string,
    type: string,
    content: string,
    createdDate: Date,
    updatedDate: Date,
    confirmed: boolean
}
